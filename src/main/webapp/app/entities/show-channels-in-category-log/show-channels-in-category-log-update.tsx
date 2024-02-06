import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './show-channels-in-category-log.reducer';
import { IShowChannelsInCategoryLog } from 'app/shared/model/show-channels-in-category-log.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IShowChannelsInCategoryLogUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ShowChannelsInCategoryLogUpdate = (props: IShowChannelsInCategoryLogUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { showChannelsInCategoryLogEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/show-channels-in-category-log');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...showChannelsInCategoryLogEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gidApp.showChannelsInCategoryLog.home.createOrEditLabel" data-cy="ShowChannelsInCategoryLogCreateUpdateHeading">
            Create or edit a ShowChannelsInCategoryLog
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : showChannelsInCategoryLogEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="show-channels-in-category-log-id">ID</Label>
                  <AvInput id="show-channels-in-category-log-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="idChannelLabel" for="show-channels-in-category-log-idChannel">
                  Id Channel
                </Label>
                <AvField
                  id="show-channels-in-category-log-idChannel"
                  data-cy="idChannel"
                  type="string"
                  className="form-control"
                  name="idChannel"
                />
              </AvGroup>
              <AvGroup>
                <Label id="nameChannelLabel" for="show-channels-in-category-log-nameChannel">
                  Name Channel
                </Label>
                <AvField id="show-channels-in-category-log-nameChannel" data-cy="nameChannel" type="text" name="nameChannel" />
              </AvGroup>
              <AvGroup>
                <Label id="idCategoryLabel" for="show-channels-in-category-log-idCategory">
                  Id Category
                </Label>
                <AvField
                  id="show-channels-in-category-log-idCategory"
                  data-cy="idCategory"
                  type="string"
                  className="form-control"
                  name="idCategory"
                />
              </AvGroup>
              <AvGroup>
                <Label id="nameCategoryLabel" for="show-channels-in-category-log-nameCategory">
                  Name Category
                </Label>
                <AvField id="show-channels-in-category-log-nameCategory" data-cy="nameCategory" type="text" name="nameCategory" />
              </AvGroup>
              <AvGroup check>
                <Label id="isShowChannelLabel">
                  <AvInput
                    id="show-channels-in-category-log-isShowChannel"
                    data-cy="isShowChannel"
                    type="checkbox"
                    className="form-check-input"
                    name="isShowChannel"
                  />
                  Is Show Channel
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="scoreChannelLabel" for="show-channels-in-category-log-scoreChannel">
                  Score Channel
                </Label>
                <AvField
                  id="show-channels-in-category-log-scoreChannel"
                  data-cy="scoreChannel"
                  type="string"
                  className="form-control"
                  name="scoreChannel"
                />
              </AvGroup>
              <AvGroup>
                <Label id="commentLabel" for="show-channels-in-category-log-comment">
                  Comment
                </Label>
                <AvField id="show-channels-in-category-log-comment" data-cy="comment" type="text" name="comment" />
              </AvGroup>
              <AvGroup check>
                <Label id="oldIsShowChannelLabel">
                  <AvInput
                    id="show-channels-in-category-log-oldIsShowChannel"
                    data-cy="oldIsShowChannel"
                    type="checkbox"
                    className="form-check-input"
                    name="oldIsShowChannel"
                  />
                  Old Is Show Channel
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="oldScoreChannelLabel" for="show-channels-in-category-log-oldScoreChannel">
                  Old Score Channel
                </Label>
                <AvField
                  id="show-channels-in-category-log-oldScoreChannel"
                  data-cy="oldScoreChannel"
                  type="string"
                  className="form-control"
                  name="oldScoreChannel"
                />
              </AvGroup>
              <AvGroup>
                <Label id="oldCommentLabel" for="show-channels-in-category-log-oldComment">
                  Old Comment
                </Label>
                <AvField id="show-channels-in-category-log-oldComment" data-cy="oldComment" type="text" name="oldComment" />
              </AvGroup>
              <AvGroup>
                <Label id="dateLogLabel" for="show-channels-in-category-log-dateLog">
                  Date Log
                </Label>
                <AvField id="show-channels-in-category-log-dateLog" data-cy="dateLog" type="date" className="form-control" name="dateLog" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/show-channels-in-category-log" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  showChannelsInCategoryLogEntity: storeState.showChannelsInCategoryLog.entity,
  loading: storeState.showChannelsInCategoryLog.loading,
  updating: storeState.showChannelsInCategoryLog.updating,
  updateSuccess: storeState.showChannelsInCategoryLog.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ShowChannelsInCategoryLogUpdate);
